function c = interpvan(x,y)
%  c=interpvan(x,y) returns a vector of
%  coefficients c = [c1, . . . , cn] given input vectors 
%  x = [x1, x2, . . . , xn]
%  y = [y1, y2, . . . , yn]

%%%%%% Cheater way %%%%
%   n= length(x)-1;
%   c= polyfit(x, y,n);
%%%%%

    n = length(x);
    V = ones(n,n);
    for j=2:n   
    %Set up column j
        V(:,j) = x.*V(:,j-1);
    end

    c = V\y; 

end
