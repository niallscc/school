function[kexp1]= Problem4b()
    % write a matlab function that solves a given matrix
    % for two different values for b;

    A=[ -47,35,0,0;15,-47,35,0;0,15,-47,35; 0, 0,15,-47];
    b1= [0;0;0;35];
   
    %Random Vector
    br1 = rand([4,1]);
    
    %b + change in b= bdelta 
    bd1=b1+br1;
    
    %initial solution of matrix
    X1= A\b1;
    
    %Change in X using the delta b values
    XD1=A\bd1;
    
    %Calculating Rout
    rOut1=norm(XD1)/norm(X1);
    
    %Calculating Rin
    
    %rIn1= norm(bd1)/norm(b1);
    %rIn1= maxNorm(bd1)/maxNorm(b1);
    rIn1= 2-norm(bd1)/2-norm(b1);
    %calculating the conditioning
    kexp1=rOut1/rIn1;
    
end