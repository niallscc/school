% Part 4: An alternative design with passive control
format long
clear
y = [(5*pi)/180, 0];
t(1) = 0;
timesteps = 1000;
hold on
omega = [14 16 24 34 44 54];

A = [0.18 0.2 0.22 0.24];

for a = 1:length(A)
   for b = 1:length(omega)
       Tend = 60*pi/omega(b);
       h = Tend/(timesteps-1);
       k=1;
       while k < timesteps 
            t(k+1) = t(k) + h;
            y(k+1,:) = AltDesignrk4(t(k),y(k,:),h,omega(b),A(a));
            k=k+1;
       end
       
       %plot(t,y(:,1),'b',t,y(:,2),'g')
       plot(y(:,1),y(:,2),'r')      
   end 
end
