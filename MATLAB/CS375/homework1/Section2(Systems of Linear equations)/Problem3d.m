function Problem3d()

%Solves problem 3d of the homework 
%idea is that we loop though the eiffel tower and add a force to each node
%and record which node gives the highest displacement. that node we return 
%this method will use the fact that A is sparse to optimize the calculation
%with A being sparse and using standard Gauss: 1.09: gain of 8 seconds
%with A being sparse and using LU, with back and forwards subs: .63 gain of
%9 seconds. 
    load('eiffel4.mat');
    maxX=0;
    numNodes= length(A); 
    trussplot(xnod, ynod, bars, 'g');
    T=cputime;
    L,U,P]= lu(A);    
    for i=1:numNodes
    
        B=zeros(numNodes,1);
        B(i)=1;
        A=sparse(A);
        %naieve way of doing this. 
        %X=A\B;       
        % using LU and backwards and forwards subs

        L=sparse(L);
        C=L\(P*B);
        X= U\C;
        
        xload = xnod + X(1:2:end);
        yload = ynod + X(2:2:end);
        
        if norm(X) > maxX 
            
            maxX= xload;
            maxY= yload;
            
        end 
    end
    
    runtime= cputime-T
    
    trussplot(maxX, maxY, bars, 'r');
end
%How does the loading in this problem relate to the perturbations
%in the previous problem? Could there be a loading with norm one 
%that gives a bigger deflection of the tower?

%Answer for problem 3e. This problem relates to the pertubations in the
%previous problem because we can see that, while last time we were looking
%at big O being an amount of error, while here we are looking at big O
%being a running time. I do not believe that there could be a norm that
%gives a bigger deflection. On the basis of this code being awesome. 



