function Problem4a()
% write a matlab function that solves a given matrix
% for two different values for b;

    A=[ -47,35,0,0;15,-47,35,0;0,15,-47,35; 0, 0,15,-47];
    b1= [0;0;0;35];
    b2=[40;-60;60;-30];

    X1= A\b1
    X2=A\b2
end