package class101.foo.io;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCacheService {

    private final PostRepository postRepository;
    private Page<Post> firstPostPage;

    @Scheduled(cron = "* * * * * *")
    public void updateFirstPostPage() {
        PageRequest request = PageRequest.of(0, 20, Sort.by("id").descending());
        firstPostPage = postRepository.findAll(request);
    }

    public Page<Post> getFirstPostPage() {
        return firstPostPage;
    }
}