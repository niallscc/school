function Problem3a()

    load('eiffel1.mat');
    trussplot(xnod,ynod, bars,'g');
    B=zeros(522,1);
    B(64)=1;
    
    X=A\B;
    xload = xnod + X(1:2:end); 
    yload = ynod + X(2:2:end);
    trussplot(xload, yload, bars,'r');

end    