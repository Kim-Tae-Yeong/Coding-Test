아래와 같이 bfs 실행 시 시간 초과

    while(len(q) != 0):
    
        node = q.popleft()
    
        visited[node] = True
    
        for elem in graph[node]:
    
          if(not visited[elem]):
      
            q.append(elem)
