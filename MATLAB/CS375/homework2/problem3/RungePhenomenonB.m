function RungePhenomenonB()
    n = [10,20,40];
    for i=1:3 
        
        for j=1:n(i)
            
            x(j)=cos((2*j-1)*pi/(2*n(i)));
        end
        
       y= 1./(1+16*x.^2); 
      
        P = vander(x)\y';
        xev = linspace(-1,1,1000)';
        yev = polyval(P,xev);
        ytrue = 1./(1+16*xev.^2);

        
        plot(x,y,'ko',xev,yev,'r-.',xev,ytrue,'g-')
        legend('Data points','Interpolating polynomial','y = 1./(1+16x.^2)')
        grid on
        title('Runge phenomenon')
        hold on
        figure();
    end
end

