function x = utrisol( U, b )
% a method for performing back substitution on an upper triangular matrix
    [M,N]=size(U);
    x = zeros(N,1);
    
    x(N) = b(N)/U(N,N);

    for j=N-1:-1:1,
        x(j) = (b(j)-U(j,j+1:N)*x(j+1:N))/U(j,j);
    end
end

