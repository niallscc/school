function f = PassiveControlFunction(t,w)
f = [w(2), ((9.81)*(sin(w(1) - sin(60*t))))/0.4];
end