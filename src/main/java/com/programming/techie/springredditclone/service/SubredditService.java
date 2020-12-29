package com.programming.techie.springredditclone.service;

import com.programming.techie.springredditclone.dto.SubredditDto;
import com.programming.techie.springredditclone.exceptions.SpringRedditException;
import com.programming.techie.springredditclone.mapper.SubredditMapper;
import com.programming.techie.springredditclone.model.Post;
import com.programming.techie.springredditclone.model.Subreddit;
import com.programming.techie.springredditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    
    Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }
    public Subreddit mapSubredditDto(SubredditDto subredditDto) {
    	Subreddit subreddit= new Subreddit();
    	subreddit.setName(subredditDto.getName());
    	subreddit.setDescription(subreddit.getDescription());
    	return subreddit;
    }
    public SubredditDto mapSubredditToDto(Subreddit subreddit) {
    	SubredditDto subredditDto = new SubredditDto();
    	subredditDto.setNumberOfPosts(mapPosts(subreddit.getPosts()));
    	subredditDto.setId(subreddit.getId());
    	subredditDto.setName(subreddit.getName());
    	subredditDto.setName(subreddit.getName());
    return subredditDto;
    	
    }

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit save = subredditRepository.save(mapSubredditDto(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(this::mapSubredditToDto)
                .collect(toList());
    }

    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        return mapSubredditToDto(subreddit);
    }
}
