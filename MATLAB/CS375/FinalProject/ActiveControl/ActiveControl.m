% % Part 3: Active Control
% clear
% format long
% y = [(5*pi)/180, 0];
% t(1) = 0;
% timesteps = 100;
% h = (4*pi)/timesteps;
% a = 0.99999;
% 
% for k = 1:timesteps
%    t(k+1) = t(k) + h;
%    y(k+1,:) = ACrk4(t(k),y(k,:),h,a);
% end
% plot(t,y)
% xlabel('Time (Seconds)')
% ylabel('Angle (Radians)')
% 
clear
format long
y = [(5*pi)/180, 0];
t(1) = 0;
timesteps = 1000;
h = (8*pi)/timesteps;
a = 3.01;

for k = 1:timesteps
   t(k+1) = t(k) + h;
   y(k+1,:) = ACrk4(t(k),y(k,:),h,a);
end
plot(t,y)
xlabel('Time (Seconds)')
ylabel('Angle (Radians)')
hold on; 
ACPlotter( y, t);

% clear
% format long
% y = [(5*pi)/180, 0];
% t(1) = 0;
% timesteps = 10000;
% h = (8*pi)/timesteps;
% a = 1.01;
% 
% for k = 1:timesteps
%    t(k+1) = t(k) + h;
%    y(k+1,:) = ACrk4(t(k),y(k,:),h,a);
% end
% plot(t,y)
% xlabel('Time (Seconds)')
% ylabel('Angle (Radians)')