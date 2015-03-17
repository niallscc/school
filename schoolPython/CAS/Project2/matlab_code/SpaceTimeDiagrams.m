%Import comma delimited data as a matrices

%Low Density configuration
%Create Figure:
low=st22(:,3:123)

imagesc(low)
%Matlab gray colormap codes 0s as white and 1s as black so the array must
%be flipped by using flipud
colormap(flipud(gray))

xlabel('Site','FontSize',20)
ylabel('Time','FontSize',20)

%Density Values
lowdensity=(sum(low,2))./121

%Density of Initial Configuration before rule application
lowdensity(1)

%High Density configuration

high=st27(:,3:123)

imagesc(high)
colormap(flipud(gray))

xlabel('Site','FontSize',20)
ylabel('Time','FontSize',20)

%Density Values
highdensity=(sum(high,2))./121

%Density of Initial Configuration before rule application
highdensity(1)