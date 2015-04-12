function x= ltrisol( L, b )
% Lower triangular matrix solver.     
    n = length( b );
    x = zeros( n, 1 );
    for i=1:n
        x(i) = ( b(i) - L(i, :)*x )/L(i, i);
    end
end

