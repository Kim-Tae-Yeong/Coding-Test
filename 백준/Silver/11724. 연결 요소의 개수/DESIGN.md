while(len(q) != 0):

    node = q.popleft()
    
    visited[node] = True
    
    for elem in graph[node]:
    
      if(not visited[elem]):
      
        q.append(elem)

위와 같이 dfs 실행시 시간 초과
