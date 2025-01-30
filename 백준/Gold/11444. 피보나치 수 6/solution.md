$$
\begin{bmatrix} F(n) \\
F(n-1) \end{bmatrix}
\=
\begin{bmatrix} F(n-1) + F(n-2) \\
F(n-1) \end{bmatrix}
$$

$$
\begin{bmatrix} F(n) \\
F(n-1) \end{bmatrix}
\=
\begin{bmatrix} 1 & 1 \\
1 & 0 \end{bmatrix}
\begin{bmatrix} F(n-1) \\
F(n-2) \end{bmatrix}
$$

$$
\begin{bmatrix} F(n) \\
F(n-1) \end{bmatrix}
\=
A^{(n-1)} \times \begin{bmatrix} F(1) \\
F(0) \end{bmatrix}
$$
