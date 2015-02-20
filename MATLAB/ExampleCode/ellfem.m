% Program 8.4 Finite element solver for elliptic equation
% Input: rectangle domain [xl,xr]x[yb,yt], covered by MxN grid
% Output: matrix w holding solution values on MxN grid
% Example usage: w=ellfem(0,1,1,2,10,10) 
function w=ellfem(xl,xr,yb,yt,M,N)
m=M-1;n=N-1;
x=xl+(xr-xl)*(0:M)/M;             % set mesh values
y=yb+(yt-yb)*(0:N)/N;
a=zeros(m*n,m*n);b=zeros(m*n,1);  % initialize str. matrix and load
% Set (x,y) coordinates of vertices
for i=1:m
  for j=1:n
    v(m*(j-1)+i,:) = [x(i+1) y(j+1)];
  end
end 
for i=1:m+2
  v(m*n+i,:) = [x(i) y(1)];
  v((m+1)*(n+1)+i+1,:) = [x(m+3-i) y(n+2)];
end 
for j=1:n
  v(m*(n+1)+j+2,:) = [x(m+2) y(j+1)];
  v((m+1)*(n+2)+j+2,:) = [x(1) y(n+2-j)];
end
% Define triangle/vertex inclusion matrix nc
nc=zeros((m+2)*(n+2),3);
for i=2:m  % CORE
  for j=1:n-1
    nc(2*j*(m+1)+2*i-1,:) = [m*(j-1)+i-1 m*j+i-1 m*j+i];
    nc(2*j*(m+1)+2*i,:) = [m*(j-1)+i-1 m*(j-1)+i m*j+i];
  end
end
for i=2:m  % BOTTOM
  nc(2*i-1,:) = [i-1 i m*n+i];
  nc(2*i,:) = [i m*n+i m*n+i+1];
end
for j=2:n  % RIGHT
  nc(2*j*(m+1)-1,:) = [(j-1)*m j*m m*(n+1)+j+2];
  nc(2*j*(m+1),:) = [(j-1)*m m*(n+1)+j+1 m*(n+1)+j+2];
end
for i=2:m  % TOP
  nc(2*n*(m+1)+2*i-1,:) = [(n-1)*m+i-1 m*(n+2)+n+4-i m*(n+2)+n+5-i];
  nc(2*n*(m+1)+2*i,:) = [(n-1)*m+i-1 (n-1)*m+i m*(n+2)+n+4-i];
end
for j=2:n  % LEFT
  nc(2*(j-1)*(m+1)+1,:) = [(j-1)*m+1 m*(n+2)+2*n+5-j m*(n+2)+2*n+6-j];
  nc(2*(j-1)*(m+1)+2,:) = [(j-2)*m+1 (j-1)*m+1 m*(n+2)+2*n+6-j];
end
% CORNERS
nc(1,:) = [1 m*n+1 m*(n+2)+2*n+4]; 
nc(2,:) = [1 m*n+1 m*n+2];
nc(2*m+1,:) = [m m*(n+1)+1 m*(n+1)+3]; 
nc(2*m+2,:) = [m*(n+1)+1 m*(n+1)+2 m*(n+1)+3]; 
nc(2*n*(m+1)+1,:) = [m*(n+2)+n+3 m*(n+2)+n+4 m*(n+2)+n+5]; 
nc(2*n*(m+1)+2,:) = [m*(n-1)+1 m*(n+2)+n+3 m*(n+2)+n+5]; 
nc(2*(n+1)*(m+1)-1,:) = [m*n m*(n+1)+n+3 m*(n+1)+n+4];
nc(2*(n+1)*(m+1),:) = [m*n m*(n+1)+n+2 m*(n+1)+n+3];
for i=1:m+2                        % use Dirichlet conditions
  c(m*n+i) = gbottom(v(m*n+i,1));
  c((m+1)*(n+1)+1+i) = gtop(v((m+1)*(n+1)+1+i,1));
end 
for j=1:n
  c(m*(n+1)+2+j) = gright(v(m*(n+1)+2+j,2));
  c((m+1)*(n+2)+2+j) = gleft(v((m+1)*(n+2)+2+j,2));
end 
mn=m*n;
% Cycle through triangles
for t=1:2*(m+1)*(n+1)
  d=abs(det([1 1 1;v(nc(t,1),:)' v(nc(t,2),:)' v(nc(t,3),:)']));
  bary = (v(nc(t,1),:)+v(nc(t,2),:)+v(nc(t,3),:))/3;
  for i=1:3
    j=mod(i,3)+1;
    k=mod(i+1,3)+1;
    if(nc(t,i)<=mn)
      a(nc(t,i),nc(t,i))=a(nc(t,i),nc(t,i))...
          +((v(nc(t,j),2)-v(nc(t,k),2))^2 ...
          +(v(nc(t,j),1)-v(nc(t,k),1))^2)/(2*d)-r(bary)*d/18;
      b(nc(t,i))=b(nc(t,i))-f(bary)*d/6;
    end
  end
  for i=1:3
    j=mod(i,3)+1;
    k=mod(i+1,3)+1;
    if(nc(t,i)<=mn & nc(t,j)<=mn)
      a(nc(t,i),nc(t,j))=a(nc(t,i),nc(t,j))...
        -((v(nc(t,i),1)-v(nc(t,k),1))*(v(nc(t,j),1)-v(nc(t,k),1))...
        +(v(nc(t,i),2)-v(nc(t,k),2))...
        *(v(nc(t,j),2)-v(nc(t,k),2)))/(2*d)-r(bary)*d/18;
      a(nc(t,j),nc(t,i))=a(nc(t,i),nc(t,j));
    end
    if(nc(t,i)<=mn & nc(t,j) > mn)
      b(nc(t,i))=b(nc(t,i))+c(nc(t,j))*((v(nc(t,i),1)-v(nc(t,k),1))...
        *(v(nc(t,j),1)-v(nc(t,k),1))+(v(nc(t,i),2)-v(nc(t,k),2))...
        *(v(nc(t,j),2)-v(nc(t,k),2)))/(2*d)+r(bary)*d/18;
    end
    if(nc(t,i)<=mn & nc(t,k) > mn)
      b(nc(t,i))=b(nc(t,i))+c(nc(t,k))*((v(nc(t,i),1)-v(nc(t,j),1))...
        *(v(nc(t,k),1)-v(nc(t,j),1))+(v(nc(t,i),2)-v(nc(t,j),2))...
        *(v(nc(t,k),2)-v(nc(t,j),2)))/(2*d)+r(bary)*d/18;
    end
  end
end 
u=a\b; 
for i=1:m
    for j=1:n
       w1(i+(j-1)*m)=log(x(i+1)^2+y(j+1)^2);
       w(i,j)=u(i+(j-1)*m);
    end
end
w1=[gbottom(x(2:M))' w gtop(x(2:M))']; % plot
w1=[gleft(y);w1;gright(y)];
mesh(x,y,w1')

function u=f(x)
u=0;

function u=r(x)
u=0;

function u=gbottom(x)
%Use dot notation
u=log(x.^2+1);

function u=gtop(x)
u=log(x.^2+4);

function u=gleft(y)
u=2*log(y);

function u=gright(y)
u=log(y.^2+1);
