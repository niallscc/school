function time= avgWaitTime(A)
    T=0;
    for i= 1:10
            B=zeros(length(A));
            x = ceil((length(A))*rand); 
            B(x)=1;
              
            t = cputime; 
            A\B; 
            T= T + cputime-t;  
          
    end
    time= T/10;
end
    