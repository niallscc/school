% Read in data on fitness and rules with neighborhood size 3 from a comma delimited file 
%call 3 radius data d3
d31=d3(:,3:130)  
d3density=(sum(d31,2))./128 %density for all but first two columns which are generations and fitness
d3(:,131)=d3density         %insert density values as last column
n3=[d3(:,1),d3(:,2),d3(:,131)]  % create new matrix with just generation, fitness, density
x=sortrows(n3,[1,-2])      % sort the matrix by generation and then descending by fitness - highest fitness first)
[~,idx] = unique(x(:,1));   %unique first row value (generation)
x = x(idx,:)                %keep only those first unique row values ( highest fitness)


gen125=x(1:25,3)
gen2550=x(25:50,3)
% gen2030=x(50:75,:)
% gen3040=x(31:40,:)
% gen4050=x(41:50,:)

hist(gen125(:))
xlabel('Lambda','FontSize',15)
ylabel('Number of Elite Fitness Rules','FontSize',15)
title('Generations 1-25','FontSize',15)

hist(gen2550(:))
xlabel('Lambda','FontSize',15)
ylabel('Number of Elite Fitness Rules','FontSize',15)
title('Generations 25-50','FontSize',15)


%Radius 2
d21=d2(:,3:34)  
d2density=(sum(d21,2))./32 %density for all but first two columns which are generations and fitness
d2(:,35)=d2density         %insert density values as last column
n2=[d2(:,1),d2(:,2),d2(:,35)]  % create new matrix with just generation, fitness, density
y=sortrows(n2,[1,-2])      % sort the matrix by generation and then descending by fitness - highest fitness first)
[~,idx] = unique(y(:,1));   %unique first row value (generation)
y = y(idx,:)                %keep only those first unique row values ( highest fitness)


geny125=y(1:25,3)
geny2550=y(25:50,3)
% gen2030=x(50:75,:)
% gen3040=x(31:40,:)
% gen4050=x(41:50,:)

bar(hist(gen125(:))./sum(hist(gen125(:))
hist(geny125(:))
xlabel('Lambda','FontSize',15)
ylabel('Number of Elite Fitness Rules','FontSize',15)
title('Generations 1-25','FontSize',15)

hist(geny2550(:))
xlabel('Lambda','FontSize',15)
ylabel('Number of Elite Fitness Rules','FontSize',15)
title('Generations 25-50','FontSize',15)