% Program 2.3 Broyden's Method II
% Input: k = max steps, x0, x1 initial vectors
% Output: solution x
% Example usage: broyden2(10,[1;2],[2;1])
function x=broyden2(k,x0,x1)
[n,m]=size(x0);
b=eye(n,n);           % initial b
for i=2:k
  del=x1-x0;delta=f(x1)-f(x0);
  b=b+(del-b*delta)*del'*b/(del'*b*delta);
  x=x1-b*f(x1);
  x0=x1;x1=x;
end

function y=f(x)
y=zeros(2,1);
y(1)=x(2)-x(1)^3;
y(2)=x(1)^2+x(2)^2-1;
