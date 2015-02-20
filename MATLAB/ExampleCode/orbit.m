%Program 6.4 Plotting program for one-body problem 
% Inputs: int=[a b] time interval, initial conditions
% ic = [x0 vx0 y0 vy0], x position, x velocity, y pos, y vel,
% h = stepsize, p = steps per point plotted
% Calls a one-step method such as trapstep.m
% Example usage: orbit([0 100],[0 1 2 0],0.01,5)
function z=orbit(int,ic,h,p)
n=round((int(2)-int(1))/(p*h));        % plot n points 
x0=ic(1);vx0=ic(2);y0=ic(3);vy0=ic(4); % grab initial conds
y(1,:)=[x0 vx0 y0 vy0];t(1)=int(1);    % build y vector
set(gca,'XLim',[-5 5],'YLim',[-5 5],'XTick',[-5 0 5],'YTick',...
  [-5 0 5],'Drawmode','fast','Visible','on','NextPlot','add');
cla;
sun=line('color','y','Marker','.','markersize',25,...
  'xdata',0,'ydata',0);
drawnow;
head=line('color','r','Marker','.','markersize',25,...
  'erase','xor','xdata',[],'ydata',[]);
tail=line('color','b','LineStyle','-','erase','none',...
  'xdata',[],'ydata',[]);
%[px,py,button]=ginput(1);         % include these three lines
%[px1,py1,button]=ginput(1);       % to enable mouse support
%y(1,:)=[px px1-px py py1-py];     % 2 clicks set direction
for k=1:n
  for i=1:p
    t(i+1)=t(i)+h;
    y(i+1,:)=eulerstep(t(i),y(i,:),h);
  end
  y(1,:)=y(p+1,:);t(1)=t(p+1);
  set(head,'xdata',y(1,1),'ydata',y(1,3))
  set(tail,'xdata',y(2:p,1),'ydata',y(2:p,3))
  drawnow;
end

function y=eulerstep(t,x,h)
%one step of the Euler method
y=x+h*ydot(t,x);

function z = ydot(t,x)
m2=3;g=1;mg2=m2*g;px2=0;py2=0;
px1=x(1);py1=x(3);vx1=x(2);vy1=x(4);
dist=sqrt((px2-px1)^2+(py2-py1)^2);
z=zeros(1,4);
z(1)=vx1;
z(2)=(mg2*(px2-px1))/(dist^3);
z(3)=vy1;
z(4)=(mg2*(py2-py1))/(dist^3);
