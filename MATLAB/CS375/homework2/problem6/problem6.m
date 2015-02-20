function problem6( )

    % model is M(d) = a0+a1*cos((2*pi*d)/T)+b1*sin((2*pi*d)/T)
    % For T we will use is:12 because that is 
    % the number of months in a year
    % times taken from http://aa.usno.navy.mil/data/docs/Dur_OneYear.php
    
    times= [591, 629, 687, 756, 818, 862, 868, 833, 773, 708, 643, 598];
    months= (1:12);

    T=12;
    
    for k=1:12
        for d= 1:12

            counter=2;
            j=1;

            M(d, 1)= 1;
            while j < k   
                M(d, counter)= cos(j*((2*pi*d)/T));
                counter = counter+1;

                M(d, counter)= sin(j*((2*pi*d)/T));
                counter = counter+1;

                j=j+1;
            end
        end
        
        
        y = M\times';
        residual(k)= norm(M*y - times');
        
    end
    residual'
    plot (months, log10(residual));
   
end

