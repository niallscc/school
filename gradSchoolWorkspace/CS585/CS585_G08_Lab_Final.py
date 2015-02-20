#
#   Group 07, Nialls Chavez, Busayo Ojumo
#   Grad Students. Please see the readme.txt
#   file for information on running out code
#
from socket import *
import os
import sys
import struct
import time
import select
import binascii
import signal
import sys
import numpy

ICMP_ECHO_REQUEST = 8
sent_pings = []
received_pings = []
ping_site = "google.com"


def checksum(str):
    csum = 0
    countTo = (len(str) / 2) * 2

    count = 0
    while count < countTo:
        thisVal = ord(str[count + 1]) * 256 + ord(str[count])
        csum = csum + thisVal
        csum = csum & 0xffffffff
        count = count + 2

    if countTo < len(str):
        csum = csum + ord(str[len(str) - 1])
        csum = csum & 0xffffffff

    csum = (csum >> 16) + (csum & 0xffff)
    csum = csum + (csum >> 16)
    answer = ~csum
    answer = answer & 0xffff
    answer = answer >> 8 | (answer << 8 & 0xff00)
    return answer


def receiveOnePing(mySocket, ID, timeout, destAddr, num_pings):
    timeLeft = timeout
    global received_pings
    while True:
        #Get the time that we are starting
        #the ping at for reporting to the user
        startedSelect = time.time()
        whatReady = select.select([mySocket], [], [], timeLeft)
        howLongInSelect = (time.time() - startedSelect)
        if whatReady[0] == []:  # Timeout
            return "Request timed out."

        timeReceived = time.time()
        recPacket, addr = mySocket.recvfrom(1024)

        # Fetch the header fromt the IP, specifially ICMPHeader
        header = recPacket[20:28]
        rawTTL = struct.unpack("s", recPacket[8])[0]
        # binascii -- Convert between binary and ASCII, this is an import
        # I am so glad we dont have to be the ones to do this.
        TTL = int(binascii.hexlify(str(rawTTL)), 16)

        type_icmp, response_code, checksum, packetID, sequence = struct.unpack(
            "bbHHh", header)
        if packetID == ID:
            bytes = struct.calcsize("d")
            timeSent = struct.unpack("d", recPacket[28:28 + bytes])[0]
            received_pings.append((timeReceived - timeSent) * 1000)
            return "%s: icmp_count:%i time=%f5ms bytes=%d TTL=%d" % (
                destAddr,
                num_pings,
                (timeReceived - timeSent) * 1000,
                len(recPacket),
                TTL)
        elif response_code is 0:
            return "Net Unreachable"
        elif response_code is 1:
            return "Host Unreachable"
        elif response_code is 2:
            return "Protocol Unreachable"
        elif response_code is 3:
            return "Port Unreachable"
        elif response_code is 4:
            return "Fragmentation Needed and Don't Fragment was Set"
        elif response_code is 5:
            return "Source Route Failed"
        elif response_code is 6:
            return "Destination Network Unknown"
        elif response_code is 7:
            return "Destination Host Unknown"
        elif response_code is 8:
            return "Source Host Isolated"
        elif response_code is 9:
            return "Communication with Destination Network is Administratively Prohibited"
        elif response_code is 10:
            return "Communication with Destination Host is Administratively Prohibited"
        elif response_code is 11:
            return "Destination Network Unreachable for Type of Service"
        elif response_code is 12:
            return "Destination Host Unreachable for Type of Service"

        timeLeft = timeLeft - howLongInSelect
        if timeLeft <= 0:
            return "Request timed out."


def sendOnePing(mySocket, destAddr, ID):
    # Header is type (8), code (8), checksum (16), id (16), sequence (16)
    global sent_pings
    myChecksum = 0
    # Make a dummy header with a 0 checksum
    # struct -- Interpret strings as packed binary data
    header = struct.pack("bbHHh", ICMP_ECHO_REQUEST, 0, myChecksum, ID, 1)
    data = struct.pack("d", time.time())
    # Calculate the checksum on the data and the dummy header.
    myChecksum = checksum(header + data)

    # Get the right checksum, and put in the header
    if sys.platform == 'darwin':
        # Convert 16-bit integers from host to network  byte order
        myChecksum = htons(myChecksum) & 0xffff
    else:
        myChecksum = htons(myChecksum)

    header = struct.pack("bbHHh", ICMP_ECHO_REQUEST, 0, myChecksum, ID, 1)
    packet = header + data
    sent_pings.append(1)
    # AF_INET address must be tuple, not str
    mySocket.sendto(packet, (destAddr, 1))
    # Both LISTS and TUPLES consist of a number of objects
    # which can be referenced by their position number within the object.


def doOnePing(destAddr, timeout, num_pings):
    icmp = getprotobyname("icmp")
    # SOCK_RAW is a powerful socket type. For more details:
    # http://sock-raw.org/papers/sock_raw

    mySocket = socket(AF_INET, SOCK_RAW, icmp)

    myID = os.getpid() & 0xFFFF  # Return the current process i
    sendOnePing(mySocket, destAddr, myID)
    delay = receiveOnePing(mySocket, myID, timeout, destAddr, num_pings)

    mySocket.close()
    return delay


def ping(host, timeout=1):
    # timeout=1 means: If one second goes by without a reply from the server,
    # the client assumes that either the client's ping or the server's pong is
    # lost
    dest = gethostbyname(host)
    #this is to give us a new line before we start, makes things pretty
    print ""
    # Send ping requests to a server separated by approximately one second
    num_pings = 1
    while True:
        delay = doOnePing(dest, timeout, num_pings)
        print delay
        time.sleep(1)  # one second
        num_pings = num_pings + 1
    return delay


def signal_handler(signal, frame):
    global sent_pings
    global received_pings
    global ping_site
    packet_loss = 0.0
    print received_pings

    min_time = 0
    max_time = 0
    avg_time = 0
    stdev_time = 0

    if len(received_pings) is not 0:
        min_time = min(received_pings)
        avg_time = reduce(lambda x, y: x + y, received_pings) / len(received_pings)
        max_time = max(received_pings)
        stdev_time = numpy.std(received_pings)

    if len(sent_pings) is not len(received_pings):
        packet_loss = ((len(sent_pings) - len(received_pings)) / len(sent_pings))

    print "--- " + ping_site + " --- ping statistics"
    print "%i packets transmitted, %i packets received, %%%f packet loss " % (
        len(sent_pings),
        len(received_pings),
        packet_loss)
    print "round-trip min/avg/max/stddev = %f/%f/%f/%f" % (
        min_time,
        avg_time,
        max_time,
        stdev_time)
    sys.exit(0)

signal.signal(signal.SIGINT, signal_handler)


if len(sys.argv) is not 1:
    ping_site = sys.argv[1]
    print "PING: " + sys.argv[1]
    ping(sys.argv[1])
else:
    ping(ping_site)
