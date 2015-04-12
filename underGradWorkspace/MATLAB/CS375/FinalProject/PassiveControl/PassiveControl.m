% Part 2: Passive Control
format long

w = [(5*pi)/180, 0];
timesteps = 10000;
h = (4*pi)/timesteps;
t(1) = 0;

for k = 1:timesteps
   t(k+1) = t(k) + h;
   w(k+1,:) = PCrk4(t(k),w(k,:),h);
end

timeToFallbyAC = spline(t,w(:,1),pi/2) % these are always off by ~ 0.1?
w
plot(t,w)
xlabel('Time (Seconds)')
ylabel('Angle (Radians)')
axis([0 3*pi 0 3*pi])