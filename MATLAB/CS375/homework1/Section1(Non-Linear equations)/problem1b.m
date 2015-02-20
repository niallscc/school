H=[];
E=[];
for h= logspace( -1,-16, 100)
   H= [H; h];
   E= [E; abs(1-(exp(h)-exp(-h))/(2*h))];
end
loglog(H,E);