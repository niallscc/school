% Problem 10, using Adaptive Trapezoidal Quadrature for previous 3 problems
a = -1;
b = 1;
x = linspace(a,b);
TOL = 1e-4;

% For Number 7
figure
myFun = inline('1/(1+(x^2))');
I7 = AdaptTrap(myFun,a,b,TOL)
% PLOT ERROR HERE AS A FUNCTION OF H USING YOUR FORMULAS and shiiiiiiit

% For Number 8, alpha = pi/4
myFun = inline('sqrt(abs(x-(pi/4)))');
figure
I8alpha1 = AdaptTrap(myFun,a,b,TOL)
% alpha = 0
myFun = inline('abs(sqrt(x))');
figure
I8alpha2 = AdaptTrap(myFun,a,b,TOL)

% PLOT ERROR HERE AS A FUNCTION OF H USING YOUR FORMULAS and shiiiiiit

% For Number 9
myFun = inline('exp(sin(6*pi*x))');
figure
I9 = AdaptTrap(myFun,a,b,TOL) % this answer is 2, should be like 2.53

% PLOT ERROR HERE AS A FUNCTION OF H USING YOUR FORMULAS and shiiiiiit

