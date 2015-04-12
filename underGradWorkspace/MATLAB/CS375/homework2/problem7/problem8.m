i=1;
counter=1;
while(counter < 7)
   i=i*2;
   [y,error]=compTrap(@fun2, -1, 1, i);
   errors(counter)= error;
   h(counter)= 1/i;
   counter=counter+1;
end

plot( errors, h) ;
title(' Composite Trapezoidal');


i=1;
counter=1;
while(counter < 7)
   i=i*2;
   [y,error]=compSimpson(@fun2, -1, 1, i);
   errors(counter)= abs(error);
   h(counter)= 1/i;
   counter=counter+1;
end
figure();

plot( errors, h) ;
title(' Composite Simpson');


i=1;
counter=1;
while(counter < 7)
   i=i*2;
   [y,error]=compMidpoint(@fun2, -1, 1, i);
   errors(counter)= abs(error);
   h(counter)= 1/i;
   counter=counter+1;
end
figure();

plot( errors, h) ;
title(' Composite Midpoint');