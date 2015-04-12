function sum = AdaptTrap(myFun,a,b,TOL)
% Adaptive Quadrature Method using Trapezoidal
% Modified from book, page 271, Program 5.2.

sum = 0;
n = 1;
A(1) = a; B(1) = b;
TOL(1) = TOL;
I(1) = trap(myFun,a,b);
while n > 0
    center = (A(n)+B(n))/2;
    oldI = I(n);
    I(n) = trap(myFun,A(n),center);
    I(n+1) = trap(myFun,center,B(n));
    if abs(oldI-(I(n)+I(n+1))) < TOL(n)*3
        sum = sum + I(n) + I(n+1);
        n = n-1;
        %plot(A(n+1),myFun(A(n+1)),'k*',B(n+1),myFun(B(n+1)),'k*')
        %hold on
        %drawnow
    else
        B(n+1) = B(n);
        B(n) = center;
        A(n+1) = center;
        TOL(n) = TOL(n)/2; TOL(n+1) = TOL(n);
        n = n+1;
    end
end

function sum = trap(f,a,b) % naive trapezoidal (non-composite)
sum = (f(a)+f(b))*(b-a)/2;