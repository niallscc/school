function y = pvalvan( c,z )
%PVALVAN and returns y = p(z)
%   uses Horner's algorithm

    n = length(c);  
    m = length(z);
    y = c(n)*ones(m,1);
    for k=n-1:-1:1
        y = z.*y + c(k);
    end
end

