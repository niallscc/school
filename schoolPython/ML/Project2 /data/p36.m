class=20;
vb=61188;
trainsize=11269;
testsize=7505;
traininput=dlmread('train.data');
trainl=dlmread('train.label');
vocabulary=textread('vocabulary.txt','%s');

vc=zeros(class,vb);
[n,p]=size(traininput);
for(i=1:n)
  vc(trainl(traininput(i,1)),traininput(i,2))=vc(trainl(traininput(i,1)),traininput(i,2))+traininput(i,3);
end

alpha=1/vb;
sumvc=sum(vc,2)+alpha*vb;
vp=zeros(class,vb);
for i=1:class
  vp(i,:)=(vc(i,:)+alpha)./sumvc(i);
end

ycount=zeros(class,1);
for i=1:trainsize
  ycount(trainl(i))=ycount(trainl(i))+1;
end
yp=ycount./trainsize;
exy=zeros(class,vb);
xp=zeros(1,vb);
for i=1:vb
  exy(:,i)=(-vp(:,i).*log2(vp(:,i))-(1-vp(:,i)).*log2(1-vp(:,i))).*yp;
  xp(i)=sum(vp(:,i).*yp);
end
ex=-xp.*log2(xp)-(1-xp).*log2(1-xp);
ixy=(ex-sum(exy))';
[temp,index1]=sort(ixy,'descend');
vocabulary{index1(1:100)}
