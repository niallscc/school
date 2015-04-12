function Problem3b()
    clear all;
    
    load('eiffel1.mat');
    A1= length(A);
    t1=avgWaitTime(A)
    
    load('eiffel2.mat');
    A2= length(A);
    t2=avgWaitTime(A)
    
    load('eiffel3.mat');
    A3= length(A);
    t3=avgWaitTime(A)
    
    load('eiffel4.mat');
    A4= length(A);
    t4=avgWaitTime(A)
       
    loglog(t1, A1, t2, A2, t3, A3, t4, A4, linspace(t1, t4), linspace(A1, A4))
  
    
    %Yes this does match with the theories that we have concerning the
    %running time of functions like this one. Particularily we can see that
    %we have a running time of approximately O(n^3)
    
    
end