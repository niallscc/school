% Part 5c - Linear model (temperature decreases at constant rate)

days = [1 3 7];
temps = [63 46 41];

x = linspace(0,10);
model = 62.1786 - 3.3214*x; 

hold on
axis([0 10 35 70])
plot(days,temps,'kx')
plot(x,model,'r')
xlabel('Days of temp readings')
ylabel('Temperature records')
text(5,50,'Red line is linear model, blue is exponential')

% Part 5d - Exponential decay model
% Solving least squares model ln(c1) = k + c2t
% to find coefficients of model T = c0*exp(c1*t)
% Solving the overdetermined system should make MATLAB use least squares
% automatically (because there is no solution).

A = [1 1; 1 3; 1 7;];
b = [log(63);log(46);log(41);];
c = A\b; % c(1) is k, c(2) is c1.  
c0 = exp(c(1)) % Raise e^k to find c0
c1 = c(2) % Print out c0 and c1
expY = c0*exp(c1*x);
plot(x,expY)