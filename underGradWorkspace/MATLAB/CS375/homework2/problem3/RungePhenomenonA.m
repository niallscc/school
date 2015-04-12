function RungePhenomenonA() 
    n = [10, 20, 40];
    for i=1:3
        h = 2/n(i);
        x=(-1:h:1)';
        y = 1./(1+16*x.^2);
        P = vander(x)\y;
        xev = linspace(-1,1,1000)';
        yev = polyval(P,xev);
        ytrue = 1./(1+16*xev.^2);

        plot(x,y,'ko',xev,yev,'r-.',xev,ytrue,'g-')
        legend('Data points','Interpolating polynomial','y = 1./(1+16x.^2)')
        grid on
        title('Runge phenomenon')
        figure();
    end
end 