function plotter(x, y)
%all this function does is do all the work for me so that i dont
%have to type :P 

%   Example input x = [-1.0; 0.0; 2.0; 3.0]
%                 y = [-3.0; 2.0; 0.0; 5.0]
    %x = [-1.0; 0.0; 2.0; 3.0]
    %y = [-3.0; 2.0; 0.0; 5.0]
    c= interpvan(x, y);
    x0 = linspace(.2,.8,110)';
    y0 = pvalvan(c,x0);
    plot (x0,y0);
    
    hold on 
    plot (x(1), y(1), 'ro')
    plot (x(2), y(2), 'ro')
    plot (x(3), y(3), 'ro')
    plot (x(4), y(4), 'ro')
    
    
    grid on
    title('Plotter')
    
    
    roots(flipud(c)) 
    
end