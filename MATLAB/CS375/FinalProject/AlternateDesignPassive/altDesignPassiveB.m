clear;clc;close all
A = 0.20;

L = .4;
omega = (30:55);
%ode = @(t,y)[y(2);((9.81-A*omega^2*sin(omega*t))*sin(y(1)))/L];


counter=1; 

for b = 1:length(omega)
    t = linspace(0,60*pi/omega(b),500);
    h = t(end)-t(end-1);
    N = length(t);

    y = zeros(N,2);
    y(1,:) = [(5*pi)/180, 0];
    for k = 1:N-1
        y(k+1,:) = AltDesignrk4(t(k),y(k,:),h,omega(b),A);
    end

    %[t,y] = ode45(ode,linspace(0,60*pi/omega,5000),[5*pi/180;0]);
    %plot(t,y)

    figure(1);
    subplot(5,6,counter); 

    plot(y(:,1),y(:,2));
    str= ['A:', num2str(A), ' B: ', num2str(omega(b))];
    title (str); 

    figure(2); 

    subplot(5, 6, counter);
    plot(t,y(:,1),'b',t,y(:,2),'r');
    str= ['A: ', num2str(A), ' B: ', num2str(omega(b))];
    title (str); 
    counter=counter+1; 

end

