def solution(diffs, times, limit):
    
    start, end = diffs[0], max(diffs)
    answer, lev = 0, 0
    
    while start <= end:
        lev = (start + end) // 2
        
        tot_time = 0
        for i, diff in enumerate(diffs):
            if diff <= lev : tot_time += times[i]
            else: tot_time += (diff - lev) * (times[i - 1] + times[i]) + times[i]
        
        if tot_time > limit : 
            start = lev + 1
        else: 
            end = lev - 1
            answer = lev
        
    return answer

print(solution([1, 5, 3], [2, 4, 7], 30))