% Problem 6: This script calls newtonMV method on a few different
% parameters for the "Beads on a Thread" problem.  The answer x is the
% angles alpha1, alpha2, alpha3 in that order.

format longG
x = [pi/2 0 -pi/2]'; % An appropriate starting guess?
L3 = 1;
m1 = 1; % 1st row of parameters table
m2 = 1;
for i= 1:10
    f = [cos(x(1)) + cos(x(2)) + L3*cos(x(3)) - 2 ; sin(x(1)) + sin(x(2)) + L3*sin(x(3)) ;
         m2*tan(x(1)) - (m1+m2)*tan(x(2)) + m1*tan(x(3))]

     Jacobian = [-sin(x(1)) (-sin(x(2))) (-L3*sin(x(3))); 
                 cos(x(1)) cos(x(2)) L3*cos(x(3));
                 m2*(sec(x(1))^2)  (-(m1+m2)*(sec(x(2))^2))  m1*(sec(x(3))^2);];
    %x = newtonMV(x,f,Jacobian)
    x=x-Jacobian\f
end         
x1=x;
zx1=[cos(x(2)), cos(x(1))];
zy1=[sin(x(2)),sin(x(1))];

zx2=[cos(x(1)),cos(x(3))];
zy2=[sin(x(1)),sin(x(3))];

plot(cos(x(1)),sin(x(1)),'or')
hold on
plot(cos(x(2)),sin(x(2)),'og')
hold on
plot(cos(x(3)),sin(x(3)), 'ob')

hold on 

plot(zx1, zy1)
hold on 
plot(zx2, zy2 )

return 
x = [60 160 300]';
L3 = 1;
m1 = 1; % 2nd row of parameters table
m2 = 2;
f = [cosd(x(1)) + cosd(x(2)) + L3*cosd(x(3)); sind(x(1)) + sind(x(2)) + L3*sind(x(3));
     m2*tand(x(1)) - (m1+m2)*tand(x(2)) + m1*tand(x(3))];
Jacobian = [-sind(x(1)) (-sind(x(2))) (-L3*sind(x(3))); cosd(x(1)) cosd(x(2)) L3*cosd(x(3));
             m2*(secd(x(1))^2)  (-(m1+m2)*(secd(x(2))^2))  m1*(secd(x(3))^2);];
x2 = newtonMV(x,f,Jacobian)

% zx1=[cos(x(2)), cos(x(1))];
% zy1=[sin(x(2)),sin(x(1))];
% 
% zx2=[cos(x(1)),cos(x(3))];
% zy2=[sin(x(1)),sin(x(3))];
% 
% plot(cos(x(1)),sin(x(1)),'or')
% hold on
% plot(cos(x(2)),sin(x(2)),'og')
% hold on
% plot(cos(x(3)),sin(x(3)), 'ob')
% 
% hold on 
% 
% plot(zx1, zy1)
% hold on 
% plot(zx2, zy2 )

x = [60 160 300]';
L3 = 2;
m1 = 1; % 3rd row of parameters table
m2 = 1;
f = [cosd(x(1)) + cosd(x(2)) + L3*cosd(x(3)); sind(x(1)) + sind(x(2)) + L3*sind(x(3));
     m2*tand(x(1)) - (m1+m2)*tand(x(2)) + m1*tand(x(3))];
Jacobian = [-sind(x(1)) (-sind(x(2))) (-L3*sind(x(3))); cosd(x(1)) cosd(x(2)) L3*cosd(x(3));
             m2*(secd(x(1))^2)  (-(m1+m2)*(secd(x(2))^2))  m1*(secd(x(3))^2);];
x3 = newtonMV(x,f,Jacobian)

% zx1=[cos(x(2)), cos(x(1))];
% zy1=[sin(x(2)),sin(x(1))];
% 
% zx2=[cos(x(1)),cos(x(3))];
% zy2=[sin(x(1)),sin(x(3))];
% 
% plot(cos(x(1)),sin(x(1)),'or')
% hold on
% plot(cos(x(2)),sin(x(2)),'og')
% hold on
% plot(cos(x(3)),sin(x(3)), 'ob')
% 
% hold on 
% 
% plot(zx1, zy1)
% hold on 
% plot(zx2, zy2 )

x = [60 160 300]';
L3 = 1;
m1 = 10; % 4th row of parameters table
m2 = 1;
f = [cosd(x(1)) + cosd(x(2)) + L3*cosd(x(3)); sind(x(1)) + sind(x(2)) + L3*sind(x(3));
     m2*tand(x(1)) - (m1+m2)*tand(x(2)) + m1*tand(x(3))];
Jacobian = [-sind(x(1)) (-sind(x(2))) (-L3*sind(x(3))); cosd(x(1)) cosd(x(2)) L3*cosd(x(3));
             m2*(secd(x(1))^2)  (-(m1+m2)*(secd(x(2))^2))  m1*(secd(x(3))^2);];
x4 = newtonMV(x,f,Jacobian)
% 
% zx1=[cos(x(2)), cos(x(1))];
% zy1=[sin(x(2)),sin(x(1))];
% 
% zx2=[cos(x(1)),cos(x(3))];
% zy2=[sin(x(1)),sin(x(3))];
% 
% plot(cos(x(1)),sin(x(1)),'or')
% hold on
% plot(cos(x(2)),sin(x(2)),'og')
% hold on
% plot(cos(x(3)),sin(x(3)), 'ob')
% 
% hold on 
% 
% plot(zx1, zy1)
% hold on 
% plot(zx2, zy2 )
