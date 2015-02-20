% Program 12.3 Rayleigh Quotient Iteration
% Input: matrix A, initial (nonzero) vector x, number of steps k
% Output: eigenvalue lam and eigenvector x
function [lam,x]=rqi(A,x,k)
for j=1:k
  u=x/norm(x);              % normalize
  lam=u'*A*u;               % Rayleigh quotient
  x=(A-lam*eye(size(A)))\u; % inverse power iteration
end
x=x/norm(x);
lam=x'*A*x;                 % Rayleigh quotient
