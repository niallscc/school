function [x] = newtonMV(x,f,jacobian)
% Newton's Multivariate method for systems of nonlinear equations
% Requires input of x (a vector), a function f, and a jacobian matrix.
for i = 0:10
  dx = -jacobian\f;
  x = x + dx
end;
