function [L,U] = naivege(A)
% Returns an LU factorization of the matrix A without pivoting
% process to get a matrix into LU
% get matrix bottom of matrix canceled out
% take the operations that were completed and perform
% them on the identity matrix
    n= size(A);
    L=eye(n); %returns the identity matrix of a given size
    for k=1:n
        if (A(k,k) == 0) 
            Error('Pivoting is needed!'); 
        end
        
        L(k+1:n,k)=A(k+1:n,k)/A(k,k);
        for j=k+1:n
            A(j,:)=A(j,:)-L(j,k)*A(k,:);
        end
    end
    U=A;
end

