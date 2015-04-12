function f = ACFunction(t,y,a)
f = [y(2), (9.81*(sin(y(1)) - (a*y(1)*cos(y(1)))))/0.4];
end
