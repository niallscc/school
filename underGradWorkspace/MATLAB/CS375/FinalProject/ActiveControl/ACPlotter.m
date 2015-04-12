function ACPlotter( phi, t)
%now that we have the data, we need to interpolate it and find
%out where the rider crashes. 
    
    col=(phi(:)*180/pi)';
   

    for i= 1: length(col)
     
        if col(i) > 90
            
            toInterpY=[t(i-2); t(i-1); t(i); t(i+1);t(i+2)]
            toInterpX=[col(i-2)-90; col(i-1)-90; col(i)-90; col(i+1)-90; col(i+2)-90]
            
            break;
        end 
    end

    plot (t',(phi(:)*180/pi)'-90, 'r');
   
    plotter(toInterpY, toInterpX);
    
    legend('Actual calculation','interpolated data');
%     % the root is :
end

