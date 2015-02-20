function r= bisect(f, a, b, tol)

%  Matlab function bisect.m
%  Solves  f(x) = 0  using the bisection method
%
%  Inputs:
%        a = left endpoint of interval
%        b = right endpoint of interval
%        tol = tolerance for stopping bisection method
%-------------------------------------
    it=0;
    fa=f(a);
    fb=f(b);

    if (sign(fa)==sign(fb))
        error('Root can not be found with these choices for a and b');
    end

    while ((b-a)>tol)
        it=it+1;
        c=a+0.5*(b-a);
        fc=f(c);
        if sign(fa)~=sign(fc);
            b=c;
            fb=fc;
                        % root lies in [a,c]
        else            % root lies in [c,b]
            a=c;
            fa=fc;
        end
        %  print progress
        r=0.5*(a+b);
        err=0.5*abs(b-a);
        fprintf('\n   n = %i   Solution = %15.10e  error = %15.10e\n',it,r, err);
    end
    %  print results
    format long;
    fprintf('\n\n    Computed Solution = %e  \n\n',r);
    fprintf('    Iteration count =  %i    \n\n',it);
