% Program 6.8 Adams-Bashforth-Moulton second-order P-C
% Inputs: [inter(1),inter(2)] time interval, 
%  ic=[y0]  initial condition
%  h = stepsize, s = number of steps for explicit method
% Output: solution y
% Calls multistep methods such as ab2step.m and am1step.m
% Example usage: predcorr([0 1],1,.05,2);
function [t,y]=predcorr(inter,ic,h,s)
n=round((inter(2)-inter(1))/h);
% Start-up phase
y(1,:)=ic;t(1)=inter(1);
for i=1:s-1               % start-up phase, using one-step method
  t(i+1)=t(i)+h;
  y(i+1,:)=trapstep(t(i),y(i,:),h);
  f(i,:)=ydot(t(i),y(i,:));
end
for i=s:n                 % multistep method loop
  t(i+1)=t(i)+h;
  f(i,:)=ydot(t(i),y(i,:));
  y(i+1,:)=ab2step(t(i),i,y,f,h);  % predict
  f(i+1,:)=ydot(t(i+1),y(i+1,:));
  y(i+1,:)=am1step(t(i),i,y,f,h);  % correct
end 

function y=trapstep(t,x,h)
%one step of the Trapezoid Method from section 6.2
z1=ydot(t,x); 
g=x+h*z1; 
z2=ydot(t+h,g); 
y=x+h*(z1+z2)/2;

function z=ab2step(t,i,y,f,h)
%one step of the Adams-Bashforth 2-step method
z=y(i,:)+h*(3*f(i,:)-f(i-1,:))/2;

function z=am1step(t,i,y,f,h)
%one step of the Adams-Moulton 1-step method
z=y(i,:)+h*(f(i+1,:)+f(i,:))/2;

function z = ydot(t,y)  % IVP
z = t*y+t^3;
