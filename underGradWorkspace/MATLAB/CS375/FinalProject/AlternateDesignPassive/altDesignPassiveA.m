clear;clc;close all
A = [0.18 0.2 0.22 0.24];

L = .4;
omega = [14 16 24 34 44 54];
%ode = @(t,y)[y(2);((9.81-A*omega^2*sin(omega*t))*sin(y(1)))/L];


counter=1; 
for a = 1:length(A)
    for b = 1:length(omega)
        t = linspace(0,60*pi/omega(b),500);
        h = t(end)-t(end-1);
        N = length(t);

        y = zeros(N,2);
        y(1,:) = [(5*pi)/180, 0];
        for k = 1:N-1
            y(k+1,:) = AltDesignrk4(t(k),y(k,:),h,omega(b),A(a));
        end
        
        figure(1);
        subplot(4,6,counter); 
        
        plot(y(:,1),y(:,2));
        str= ['A:', num2str(A(a)), ' B: ', num2str(omega(b))];
        title (str); 
        
        figure(2); 
        
        subplot(4, 6, counter);
        plot(t,y(:,1),'b',t,y(:,2),'r');
        str= ['A: ', num2str(A(a)), ' B: ', num2str(omega(b))];
        title (str); 
        counter=counter+1; 
    end
end

