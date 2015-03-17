
%Density of IC vs. correct classification GKL vs. one of our fit rules

%ours is one of our fit rule data
dens=[ours(1,:),ours(3,:),ours(5,:);ours(2,:),ours(4,:),ours(6,:)]
plot(dens(1,:),dens(2,:))
xlabel('Rule Density')
ylabel('Proportion correctly classified')



%GKL Rule
gkld=[gkl(1,:),gkl(3,:),gkl(5,:);gkl(2,:),gkl(4,:),gkl(6,:)]
plot(gkld(1,:),gkld(2,:))
xlabel('Rule Density')
ylabel('Proportion correctly classified')