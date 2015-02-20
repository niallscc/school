% Program 3.6 Cubic spline plot
% Plots spline from data points
% Input: x,y vectors of data points
% Output: none
function splineplot(x,y)
n=length(x);
coeff=splinecoeff(x,y);
clf;hold on;            % clear figure window and turn hold on
for i=1:n-1
    x0=linspace(x(i),x(i+1),100);
    dx=x0-x(i);
    y0=coeff(i,3)*dx;   % evaluate using nested multiplication
    y0=(y0+coeff(i,2)).*dx;
    y0=(y0+coeff(i,1)).*dx+y(i);
    plot([x(i) x(i+1)],[y(i) y(i+1)],'o',x0,y0)
end
hold off
