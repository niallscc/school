% Program 8.3 Finite difference solver for 2D Poisson equation
%   with Dirichlet boundary conditions on a rectangle
% Input: rectangle domain [xl,xr]x[yb,yt], covered by MxN grid
% Output: matrix w holding solution values on MxN grid
% Example usage: w=poisson(0,1,1,2,10,10) 
function w=poisson(xl,xr,yb,yt,M,N)
m=M-1;n=N-1;
h=(xr-xl)/M;h2=h^2;k=(yt-yb)/N;
r=h2/k^2;s=2*(1+r);                   
x=xl+(xr-xl)*(0:M)/M;   % set mesh values
y=yb+(yt-yb)*(0:N)/N;
z=zeros(1,m-2);                        
a=zeros(m*n,m*n);b=zeros(m*n,1); % initialize structure matrix 
% load structure matrix a
% inner core
for i=2:m-1
  for j=2:n-1
    a(i+(j-1)*m,:)=[zeros(1,i-1+(j-2)*m) r z 1 -s 1 z r ...
     zeros(1,(n-j)*m-i)];
    b(i+(j-1)*m)=h2*f(x(i+1),y(j+1));
  end
end
% outer ring
j=1;                    % bottom row
for i=2:m-1
  a(i+(j-1)*m,:)=[zeros(1,i-2) 1 -s 1 z r zeros(1,(n-j)*m-i)];
  b(i+(j-1)*m)=h2*f(x(i+1),y(j+1))-r*gbottom(x(i+1));
end
j=n;                    % top row
for i=2:m-1
  a(i+(j-1)*m,:)=[zeros(1,i-1+(j-2)*m) r z 1 -s 1 zeros(1,m-i-1)];
  b(i+(j-1)*m)=h2*f(x(i+1),y(j+1))-r*gtop(x(i+1));
end
i=1;                    % left side
for j=2:n-1
  a(i+(j-1)*m,:)=[zeros(1,i-1+(j-2)*m) r z 0 -s 1 z r ...
   zeros(1,(n-j)*m-i)];
  b(i+(j-1)*m)=h2*f(x(i+1),y(j+1))-gleft(y(j+1));
end
i=m;                    % right side
for j=2:n-1
  a(i+(j-1)*m,:)=[zeros(1,(j-1)*m-1) r z 1 -s 0 z r ...
   zeros(1,(n-j)*m-i)];
  b(i+(j-1)*m)=h2*f(x(i+1),y(j+1))-gright(y(j+1));
end
% four corners
i=1;j=1;                % bottom left
a(i+(j-1)*m,:)=[-s 1 z r zeros(1,(n-1)*m-1)];
b(i+(j-1)*m)=h2*f(x(i+1),y(j+1))-r*gbottom(x(i+1))-gleft(y(j+1));
i=m;j=1;                % bottom right
a(i+(j-1)*m,:)=[z 1 -s 0 z r zeros(1,(n-2)*m)];
b(i+(j-1)*m)=h2*f(x(i+1),y(j+1))-r*gbottom(x(i+1))-gright(y(j+1));
i=1;j=n;                % top left
a(i+(j-1)*m,:)=[zeros(1,(n-2)*m) r z 0 -s 1 zeros(1,m-2)];
b(i+(j-1)*m)=h2*f(x(i+1),y(j+1))-r*gtop(x(i+1))-gleft(y(j+1));
i=m;j=n;                % top right
a(i+(j-1)*m,:)=[zeros(1,(n-1)*m-1) r z 1 -s];
b(i+(j-1)*m)=h2*f(x(i+1),y(j+1))-r*gtop(x(i+1))-gright(y(j+1));
v=a\b;                  % solve for solution
w=zeros(m,n);
for i=1:m               % put solution into mesh 
  for j=1:n
    w(i,j)=v(i+(j-1)*m);
  end
end
w1=[gbottom(x(2:M))' w gtop(x(2:M))']; % add boundary conditions 
w1=[gleft(y);w1;gright(y)];
mesh(x,y,w1')

function u=f(x,y)       % right hand side of equation
u=0;

function u=gbottom(x)   % bottom of rectangle
% Use dot notation
u=log(x.^2+1);

function u=gtop(x)      % top of rectangle
u=log(x.^2+4);

function u=gleft(y)     % left side of rectangle
u=2*log(y);

function u=gright(y)    % right side of rectangle
u=log(y.^2+1);
