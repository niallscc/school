i=1;
counter=1;
correct=compTrap(@fun2, -1, 1, 10000);
while(counter < 7)
   i=i*2;
   
   y=compTrap(@fun2, -1, 1, i);
   
   error(counter)= abs(y-correct);
   h(counter)= 1/i;
   counter=counter+1;
end

loglog(h, error) ;
title(' Composite Trapezoidal');


i=1;
counter=1;
correct=compSimpson(@fun2, -1, 1, 10000);
while(counter < 7)
   i=i*2;
   y=compSimpson(@fun2, -1, 1, i);
   errors(counter)= abs(y-correct);
   h(counter)= 1/i;
   counter=counter+1;
end
figure();

loglog(h, errors) ;
title(' Composite Simpson');


i=1;
counter=1;
correct=compMidpoint(@fun2, -1, 1, 10000);
while(counter < 7)
   i=i*2;
   y=compMidpoint(@fun2, -1, 1, i);
   errors(counter)= abs(y-correct);
   h(counter)= 1/i;
   counter=counter+1;
end
figure();

loglog(h, errors) ;
title(' Composite Midpoint');