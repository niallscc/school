% bounce.m
% Illustrates Matlab animation using the drawnow command
% Usage: Type "bounce"
set(gca,'XLim',[0 1],'YLim',[0 1],'Drawmode','fast',...
     'Visible','on');
cla
axis square
ball = line('color','r','Marker','o','MarkerSize',10,...
     'LineWidth',2,'erase','xor','xdata',[],'ydata',[]);
hx0=.005;hy0=.0039;hx=hx0;hy=hy0;
xl=.02;xr=.98;yb=xl;yt=xr;x=.1;y=.1;
while 1 == 1
    if x < xl
        hx= hx0;
    end
    if x > xr
        hx = -hx0;
    end
    if y < yb
        hy = hy0;
    end
    if y > yt
        hy = -hy0;
    end
    x=x+hx;y=y+hy;
    set(ball,'xdata',x,'ydata',y);drawnow;pause(0.01)
end