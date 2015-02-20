% Part 1: No control
format long

w = [(5*pi)/180, 0];
timesteps = 10000;
h = (4*pi)/timesteps;
t(1) = 0;

for k = 1:timesteps
   t(k+1) = t(k) + h;
   w(k+1,:) = NCrk4(t(k),w(k,:),h);
end

PeriodByRK4 = spline(t,w(:,2),pi/2)

plot(t,w)
xlabel('Time (Seconds)')
ylabel('Angle (Radians)')
axis([0 3*pi 0 3.2*pi])
return
% now doing it by numerical integration on equation 2: 

KofU = inline('1/sqrt(1-(cos(5*pi/360).^2 * sin(x).^2))'); % integral
K = AdaptTrap(KofU,0,pi/2,1e-8); % is it supposed to be pi/2 instead of pi or?
Tintegration = 2* sqrt((0.4/9.81) * K)
