%function [maxX, maxY]= Problem3c()

function Problem3c()
%Solves problem 3c of the homework 
%idea is that we loop though the eiffel tower and add a force to each node
%and record which node gives the highest displacement. that node we return 
% Solved without sparse: 9.29, solved with sparce 11.6   
% odd... using gaussian elimination i got a run time of 8.7.
% I am guessing they are doing a ton of optimization... 
    load('eiffel3.mat');
    
    A(1,:) = 1; 
    spy(A)
    [L,U,P]= lu(A);
    figure 
    spy(L)
    figure
    spy(U)
    
    return;
    maxX=0;
    numNodes= length(A); 
    A= sparse(A);
    
    
    L=sparse(L);
    trussplot(xnod, ynod, bars, 'g');
    T=cputime;
    node=0;
    for i=1:2:numNodes
        B=zeros(numNodes,1);
        
        B(i)=1;
        
        %naieve way of doing this. 
        %X=A\B;
       
        % using LU and backwards and forwards subs
        
        C=L\(P*B);
        X= U\C;
        
        xload = xnod + X(1:2:end);
        yload = ynod + X(2:2:end);
        
        if norm(X) > maxX 
            node=i;
            maxX= xload;
            maxY= yload;
            
        end 
    end
    runtime= cputime-T
    node
    trussplot(maxX, maxY, bars, 'r');
    size(maxX)
    size( maxY)
    hold on;
    plot(maxX((node-1)/2), maxY((node-1)/2), 'bo');
    
    
    
end

