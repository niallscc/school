% Program 6.2  Vector version of Euler method
% Inputs: interval [a,b], initial vector y0, step size h
% Output: time steps t, solution y
% Example usage: y=euler2([0 1],[0 1],0.1);
function [t,y]=euler2(int,y0,h)
t(1)=int(1); y(1,:)=y0;
n=round((int(2)-int(1))/h);
for i=1:n
  t(i+1)=t(i)+h;
  y(i+1,:)=eulerstep(t(i),y(i,:),h);
end
plot(t,y(:,1),t,y(:,2));


function y=eulerstep(t,y,h)
%one step of the Euler method
%Input: current time t, current vector y, step size h
%Output: the approximate solution vector at time t+h
y=y+h*ydot(t,y);

function z=ydot(t,y)
z(1) = y(2)^2-2*y(1);
z(2) = y(1)-y(2)-t*y(2)^2;
