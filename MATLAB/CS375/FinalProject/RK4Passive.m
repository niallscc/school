function RK4Passive()
%RK4 Summary of this function goes here
%   Detailed explanation goes here
%It calculates ODE using Runge-Kutta 4th order method
%Author Nialls Chavez

    clc;                        %Clears the screen
    clear all;

    h=.05;                      %step size
    t = 0:h:10;                  % Calculates upto phi(3)
    phi = zeros(2,length(t));   %settting the size of phi
    
    phi(1, 1)= (5*pi)/180;         %the angle from vertical
    phi(1,2)= 0; 
    
    
    for i=1:(length(t)-1)       %calculation loop
        k_1 = F_xy(t(i),phi(:,i));
        k_2 = F_xy(t(i)+0.5*h,phi(:,i)+0.5*h*k_1);
        k_3 = F_xy((t(i)+0.5*h),(phi(:,i)+0.5*h*k_2));
        k_4 = F_xy((t(i)+h),(phi(:,i)+k_3*h));
        
        phi(:,i+1) = phi(:,i) + (1/6)*(k_1+2*k_2+2*k_3+k_4)*h;  %main equation
        
    end
    
    %now that we have the data, we need to interpolate it and find
    %out where the rider crashes. 
   
    col=(phi(1,:)*180/pi)';
    
    hold on; 
    for i= 1: length(col)
        col(i)
        if col(i) > 90
            
            toInterpY=[t(i-2); t(i-1); t(i); t(i+1);t(i+2)];
            toInterpX=[col(i-2)-90; col(i-1)-90; col(i)-90; col(i+1)-90; col(i+2)-90];
            %plot (toInterpY, toInterpX-90, 'ro')

            break;
        end
    end

    plot (t',(phi(1,:)*180/pi)-90, 'g');
   
    plotter(toInterpY, toInterpX);
    title(' Passive control');
    legend('Actual calculation','interpolated data');
    axis([1.3 2 -150 200])
    %the roots are: 
    %2.089312193255840                     
    %1.673031032380639          => correct root             
    %1.471745118577493 + 0.231718001703095i
    %1.471745118577493 - 0.231718001703095i
end

function F= F_xy(t, phi )

    l= 0.4;                     %a constant 
    g=9.81;                     %gravity
    F = [phi(2); (g*(sin(phi(1) - sin(60*t))))/l];
    
end