%Program 10.3 Wiener filter
load handel                         % y is clean signal
c=y(1:40000);                       % work with first 40K samples
p=1.3;                              % parameter for cutoff
noise=std(c)*.50;                   % 50 percent noise
n=length(c);                        % n is length of signal
r=noise*randn(n,1);                 % pure noise
x=c+r;                              % noisy signal
fx=fft(x);sfx=conj(fx).*fx;         % take fft of signal, and
sfcapprox=max(sfx-n*(p*noise)^2,0); % apply cutoff
phi=sfcapprox./sfx;                 % define phi as derived
xout=real(ifft(phi.*fx));           % invert the fft
% then compare sound(x) and sound(xout)
