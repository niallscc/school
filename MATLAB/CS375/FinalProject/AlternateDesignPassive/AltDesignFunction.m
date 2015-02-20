function f = AltDesignFunction(t,y,omega,A)
f = [y(2), ((9.81 - A * (omega^2)*sin(omega*t))*sin(y(1)))/0.4];
end
